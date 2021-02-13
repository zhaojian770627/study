package com.zj.study.designpattern.pattern.behavioral.memento;

public class Test {

	public static void main(String[] args) {
		ArticleMementoManager articleMementoManager = new ArticleMementoManager();
		Article article = new Article("如影随行的设计模式A", "手记内容A", "手记图片A");

		ArticleMemento articleMemento = article.saveToMemento();
		articleMementoManager.addMemento(articleMemento);
		System.out.println(article + "暂存成功A");

		System.out.println("B修改手记start");
		article.setTitle("如影随行的设计模式B");
		article.setContent("手记内容B");
		article.setImgs("手记图片B");
		System.out.println("修改手记end");

		System.out.println("B手记完整内容:" + article);
		articleMemento = article.saveToMemento();
		articleMementoManager.addMemento(articleMemento);
		System.out.println(article + "暂存成功B");

		System.out.println("C修改手记start");
		article.setTitle("如影随行的设计模式C");
		article.setContent("手记内容C");
		article.setImgs("手记图片C");
		System.out.println("C手记完整内容:" + article);

		System.out.println("回退1");
		articleMemento = articleMementoManager.getMemento();
		article.undoFromMemento(articleMemento);
		System.out.println("回退1完整内容:" + article);

		System.out.println("回退2");
		articleMemento = articleMementoManager.getMemento();
		article.undoFromMemento(articleMemento);
		System.out.println("回退2完整内容:" + article);
	}

}
