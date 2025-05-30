package com.example.librarymanagementsystem.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.librarymanagementsystem.entities.Book;
import com.example.librarymanagementsystem.security.CurrentUserFinder;
import com.example.librarymanagementsystem.services.BookService;
import com.example.librarymanagementsystem.services.UserService;

@Component
public class DateTracker {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private CurrentUserFinder currentUserFinder;
    
    @Autowired
    private UserService usService;
    
    private LocalDate now = LocalDate.now();
    
    public LocalDate getNow() {
        return now;
    }
    
    public long differenceInDays(LocalDate date) {
        return ChronoUnit.DAYS.between(date, LocalDate.now());
    }
    
    public long daysTooLate(LocalDate date) {
        long dayDifference = ChronoUnit.DAYS.between(date, LocalDate.now());
        return Math.max(dayDifference, 0); // Ensure non-negative values
    }
    
    public String getReservationDatesInString(Book book) {
        String reservationDatesInString = "";
        LocalDate firstAvailableDate;
        
        if (book.getReturnDate() == null || book.getReturnDate().compareTo(LocalDate.now()) < 0) {
            reservationDatesInString += LocalDate.now().toString() + "   /   ";
            firstAvailableDate = LocalDate.now();
        } else {
            reservationDatesInString += book.getReturnDate().plusDays(1) + "   /   ";
            firstAvailableDate = book.getReturnDate().plusDays(1);
        }
        
        reservationDatesInString += firstAvailableDate.plusDays(7).toString();
        return reservationDatesInString;
    }
    
    public void setReserervationDatesAndReservedByCurrentUserForMultipleBooks(Collection<Long> bookIds) {
        for (Long bookId : bookIds) {
            setBookReservationDatesAndReservedByCurrentUser(bookId);
        }
    }
    
    public void setBookReservationDatesAndReservedByCurrentUser(Long bookId) {
        Book book = bookService.findById(bookId);
        LocalDate startReservationDate;
        
        if (book.getReturnDate() == null || book.getReturnDate().compareTo(LocalDate.now()) < 0) {
            startReservationDate = LocalDate.now();
        } else {
            startReservationDate = book.getReturnDate().plusDays(1);
        }
        
        LocalDate endReservationDate = startReservationDate.plusDays(7);
        book.setStartReservationDate(startReservationDate);
        book.setEndReservationDate(endReservationDate);
        book.setReservedByUser(usService.findById(currentUserFinder.getCurrentUserId()));
        bookService.save(book);
        usService.save(currentUserFinder.getCurrentUser());
    }
    
    public int getWeeksToExtendReturnDate(Book book) {
        long daysTooLate = this.daysTooLate(book.getReturnDate());  // ✅ Replaced `dateTracker` with `this`
        
        if (daysTooLate <= 7) return 1;
        else if (daysTooLate <= 14) return 2;
        else return 3;
    }
    
    public Map<Book, String> listedBookReservations(Collection<Long> bookIds) {
        Map<Book, String> listedBookReservations = new LinkedHashMap<>();
        
        for (Long bookId : bookIds) {
            Book reservedBookObject = bookService.findById(bookId);
            String reservationDates = this.getReservationDatesInString(reservedBookObject); // ✅ Replaced `dateTracker` with `this`
            listedBookReservations.put(reservedBookObject, reservationDates);
        }
        
        return listedBookReservations;
    }
}
















// package com.example.librarymanagementsystem.utils;

// import java.time.LocalDate;
// import java.time.temporal.ChronoUnit;
// import java.util.Collection;
// import java.util.LinkedHashMap;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// import com.example.librarymanagementsystem.entities.Book;
// import com.example.librarymanagementsystem.security.CurrentUserFinder;
// import com.example.librarymanagementsystem.services.BookService;
// import com.example.librarymanagementsystem.services.UserService;

// @Component
// public class DateTracker {

// 	@Autowired
// 	BookService bookService;
	
// 	@Autowired
// 	CurrentUserFinder currentUserFinder;
	
// 	@Autowired
// 	UserService usService;
	
// 	//@Autowired
// 	// DateTracker dateTracker;
// 	private LocalDate now = LocalDate.now();
	
// 	public LocalDate getNow() {
// 		return now;
// 	}
	
// 	public long differenceInDays(LocalDate date) {
// 		return ChronoUnit.DAYS.between(date, LocalDate.now());
// 	}
	
// 	public long daysTooLate(LocalDate date) {
// 		long dayDifference = ChronoUnit.DAYS.between(date, LocalDate.now());
// 		if (dayDifference > 0) return dayDifference;
// 		else return 0;
// 	}
	
// 	public String getReservationDatesInString(Book book) {
		
// 		String reservationDatesInString = "";
		
// 		LocalDate firstAvailableDate;
		
// 		if (book.getReturnDate() == null) {
// 			reservationDatesInString += LocalDate.now().toString() + "   /   ";
// 			firstAvailableDate = LocalDate.now();
// 		} else {
// 			if (book.getReturnDate().compareTo(LocalDate.now()) < 0){
// 				reservationDatesInString += LocalDate.now().toString() + "   /   ";
// 				firstAvailableDate = LocalDate.now();
// 			} else {
// 				reservationDatesInString += book.getReturnDate().plusDays(1) + "   /   ";
// 				firstAvailableDate = book.getReturnDate().plusDays(1);
// 			}
// 		}
// 		reservationDatesInString += firstAvailableDate.plusDays(7).toString();
		
// 		return reservationDatesInString;
// 	}
	
// 	public void setReserervationDatesAndReservedByCurrentUserForMultipleBooks(Collection<Long> bookIds) {
// 		for (Long bookId : bookIds) setBookReservationDatesAndReservedByCurrentUser(bookId);
// 	}
	
// 	public void setBookReservationDatesAndReservedByCurrentUser(Long bookId) {
// 		Book book = bookService.findById(bookId);
// 		LocalDate startReservationDate;
		
// 		if (book.getReturnDate() == null) {
// 			startReservationDate = LocalDate.now();
// 		} else {
// 			if (book.getReturnDate().compareTo(LocalDate.now()) < 0) {
// 				startReservationDate = LocalDate.now();
// 			} else {
// 				startReservationDate = book.getReturnDate().plusDays(1);
// 			}	
// 		}
		
// 		LocalDate endReservationDate = startReservationDate.plusDays(7);
// 		book.setStartReservationDate(startReservationDate);
// 		book.setEndReservationDate(endReservationDate);
// 		book.setReservedByUser(usService.findById(currentUserFinder.getCurrentUserId()));
// 		bookService.save(book);
// 		usService.save(currentUserFinder.getCurrentUser());	
// 	}
	
// 	public int getWeeksToExtendReturnDate(Book book) {
// 		long daysTooLate = dateTracker.daysTooLate(book.getReturnDate());
		
// 		int weeksToExtend;
// 		if (daysTooLate <= 7) weeksToExtend = 1;
// 		else if (daysTooLate > 7 && daysTooLate <= 14) weeksToExtend = 2;
// 		else weeksToExtend = 3;
		
// 		return weeksToExtend;
// 	}
	
// 	public Map<Book, String> listedBookReservations(Collection <Long> bookIds){
		
// 		Map<Book, String> listedBookReservations = new LinkedHashMap<Book, String>();
// 		for (Long bookId : bookIds) {
// 			Book reservedBookObject = bookService.findById(bookId);
// 			String reservationDates = dateTracker.getReservationDatesInString(reservedBookObject);
// 			listedBookReservations.put(reservedBookObject, reservationDates);
// 		}
// 		return listedBookReservations;
// 	}
// }






