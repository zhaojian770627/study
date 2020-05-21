package com.zj.study.compile.Calculator;

import com.zj.study.compile.Token;
import com.zj.study.compile.TokenReader;
import com.zj.study.compile.TokenType;
import com.zj.study.compile.ast.ASTNodeType;
import com.zj.study.compile.ast.SimpleASTNode;

public class SimpleCalculator {

	public static void main(String[] args) throws Exception {
		TokenReader tokenReader = new TokenReader("d:\\test.zj");
		tokenReader.initReader();
		SimpleCalculator calator = new SimpleCalculator(tokenReader);
		SimpleASTNode node = calator.intDeclare();
		SimpleASTNode.dumpAST(node, "-");
	}

	private TokenReader tokenReader;

	public SimpleCalculator(TokenReader tokenReader) {
		this.tokenReader = tokenReader;
	}

	/**
	 * 
	 * MatchIntDeclare(){ MatchToken(Int); // 匹配 Int 关键字 MatchIdentifier(); // 匹配标识符
	 * MatchToken(equal); // 匹配等号 MatchExpression(); // 匹配表达式 }
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 */
	public SimpleASTNode intDeclare() throws Exception {
		SimpleASTNode node = null;
		Token token = tokenReader.peek(); // 预读

		if (token != null && token.getType().equals(TokenType.INT)) { // 匹配 Int
			token = tokenReader.pop();

			if (tokenReader.peek().getType().equals(TokenType.Identifier)) { // 匹配标识符
				token = tokenReader.pop(); // 消耗掉标识符
				// 创建当前节点，并把变量名记到 AST 节点的文本值中，
				// 这里新建一个变量子节点也是可以的
				node = new SimpleASTNode(ASTNodeType.IntDeclaration, token.getTokenText());

				token = tokenReader.peek(); // 预读
				if (token != null && token.getType().equals(TokenType.EQ)) { // 匹配等号
					tokenReader.pop(); // 消耗掉等号

					SimpleASTNode child = additive(); // 匹配一个表达式
					if (child == null) {
						throw new Exception("invalide variable initialization, expecting an expression");
					} else {
						node.addChild(child);
					}
				}
			}
		}

		return node;
	}

	private SimpleASTNode additive() throws Exception {
		SimpleASTNode child1 = multiplicative(); // 计算第一个子节点
		SimpleASTNode node = child1; // 如果没有第二个子节点，就返回这个
		Token token = tokenReader.peek();
		if (child1 != null && token != null) {
			if (token.getType().equals(TokenType.Plus)) {
				token = tokenReader.pop();
				SimpleASTNode child2 = additive(); // 递归地解析第二个节点
				if (child2 != null) {
					node = new SimpleASTNode(ASTNodeType.AdditiveExp, token.getTokenText());
					node.addChild(child1);
					node.addChild(child2);
				} else {
					throw new Exception("invalid additive expression, expecting the right part.");
				}
			}
		}
		return node;
	}

	private SimpleASTNode multiplicative() {
		// 先进行基础表达式解析
		SimpleASTNode child_1 = primary();

		SimpleASTNode node = child_1;

		// 预读
		Token token = tokenReader.peek();
		if (child_1 != null && token != null) {
			if (token.getType().equals(TokenType.STAR) || token.getType().equals(TokenType.DIV)) { // 如果获取的是乘法或者除法
				// 丢弃
				token = tokenReader.pop();

				// 再次向下匹配 基础表达式
				SimpleASTNode child_2 = primary();

				if (child_2 != null) {
					// 说明乘除后面有基础表达式
					// 建立新的父节点，构造一棵乘除法子树
					node = new SimpleASTNode(ASTNodeType.Multiplicative, token.getTokenText());
					node.addChild(child_1);
					node.addChild(child_2);
				}
			}
		}
		return node;
	}

	private SimpleASTNode primary() {
		SimpleASTNode node = null;

		// 预读一位
		Token token = tokenReader.peek();
		if (token != null) {
			if (token.getType().equals(TokenType.IntLiteral)) { // 如果是整形字面量
				// 丢弃已读
				token = tokenReader.peek();
				// 创建node节点，添加类型和值
				node = new SimpleASTNode(ASTNodeType.IntLiteral, token.getTokenText());
			} else if (token.getType().equals(TokenType.Identifier)) { // 如果是变量字面量
				// 丢弃已读
				token = tokenReader.peek();
				// 创建node节点，添加类型和值
				node = new SimpleASTNode(ASTNodeType.Identifier, token.getTokenText());
			}
		}

		return node;
	}
}
