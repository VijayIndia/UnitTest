package com.hubberspot.junit5.bookstore.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hubberspot.junit5.bookstore.exception.BookNotFoundException;
import com.hubberspot.junit5.bookstore.model.Book;

public class BookService {

	private List<Book> listOfBooks = new ArrayList<>();

	public BookService(){
		Book book1=new Book("1","Title1","Publisher1");
		Book book2=new Book("2","Title2","Publisher2");
		Book book3=new Book("3","Title3","Publisher3");
		this.listOfBooks.add(book1);
		this.listOfBooks.add(book2);
		this.listOfBooks.add(book3);
	}

	public void addBook(Book book) {
		listOfBooks.add(book);
	}

	public List<Book> books(){
		return Collections.unmodifiableList(listOfBooks);
	}

	public Book getBookById(String bookId) {
		for(Book book : listOfBooks) {
			if(bookId.equals(book.getBookId())) {
				return book;
			}
		}
		return null;
	}

	public String[] getBookIdsByPublisher(String publisher) {
		List<String> bookIds = new ArrayList<>();
		for(Book book : listOfBooks) {
			if(publisher.equals(book.getPublisher())) {
				bookIds.add(book.getBookId());
			}
		}
		return bookIds.toArray(new String[bookIds.size()]);
	}

	public List<String> getBookTitlesByPublisher(List<String> publisher) {
		List<String> bookTitles = new ArrayList<>();
		System.out.println("publisher->"+publisher+"listOfBooks->"+listOfBooks);
		for(Book book : listOfBooks) {
			if(publisher.contains(book.getPublisher())) {
				bookTitles.add(book.getTitle());
			}
		}
		return bookTitles;
	}

	public void setListOfBooks(){
		Book book1=new Book("1","Title1","Publisher1");
		Book book2=new Book("2","Title2","Publisher2");
		Book book3=new Book("3","Title3","Publisher3");
		this.listOfBooks.add(book1);
		this.listOfBooks.add(book2);
		this.listOfBooks.add(book3);
	}

	public String getString(String str){
		return str;
	}

	public String getStringConsumer(Integer i){
		System.out.println("str->"+i);
		return "str"+i;
	}

	public Book getBookByTitle(String title) {
		for(Book book : listOfBooks) {
			if(title.equals(book.getTitle())) {
				return book;
			}
		}
		throw new BookNotFoundException("Book not found in Bookstore!");
	}

    public int getListOfBooks(int count){
	    return count;
    }

}
