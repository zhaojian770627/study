package com.zj.study.compile.Calculator;

import com.zj.study.compile.Token;
import com.zj.study.compile.TokenReader;
import com.zj.study.compile.TokenType;
import com.zj.study.compile.ast.ASTNodeType;
import com.zj.study.compile.ast.SimpleASTNode;

public class SimpleCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
	 * @throws Exception
	 * 
	 */
	public void intDeclare() throws Exception {
		SimpleASTNode node = null;
		Token token = tokenReader.peek(); // 预读

		if (token != null && token.getType() == TokenType.INT) { // 匹配 Int
			token = tokenReader.pop();

			// 创建当前节点，并把变量名记到 AST 节点的文本值中，
			// 这里新建一个变量子节点也是可以的
			node = new SimpleASTNode(ASTNodeType.IntDeclaration, token.getTokenText());

			token = tokenReader.peek(); // 预读
			if (token != null && token.getType() == TokenType.Assignment) { // 匹配等号
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

		return null;
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
			}
		}

		return null;
	}
}
