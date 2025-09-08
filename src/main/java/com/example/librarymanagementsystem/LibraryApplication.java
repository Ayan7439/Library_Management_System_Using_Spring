package com.example.librarymanagementsystem;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.librarymanagementsystem.entities.Book;
import com.example.librarymanagementsystem.entities.User;
import com.example.librarymanagementsystem.services.BookService;
import com.example.librarymanagementsystem.services.NotificationService;
import com.example.librarymanagementsystem.services.UserService;
import com.example.librarymanagementsystem.utils.MidnightApplicationRefresh;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Autowired
	BookService bookService;

	@Autowired
	UserService usService;

	@Autowired
	NotificationService notifService;

	@Autowired
	@Qualifier("passwordEncoder")
	BCryptPasswordEncoder pwEncoder;

	@Autowired
	MidnightApplicationRefresh midAppRef;

	@Bean
	CommandLineRunner runner() {
		return args -> {

			if (usService.count() == 0) {
				User user1 = new User("admin", pwEncoder.encode("admin1"), "martijn.reede@gmail.com", "Martijn", "Reede", "Huizumerlaan 158", "06-11433823", "Amsterdam");
				user1.setRoles(Set.of("ROLE_ADMIN", "ROLE_EMPLOYEE"));

				User user2 = new User("employee", pwEncoder.encode("test"), "cyrille.jones@gamail.com", "Cyrille", "Jones", "Hugo de Grootstraat 174", "06-87054875", "Sliedrecht");
				user2.setRoles(Set.of("ROLE_EMPLOYEE"));

				User user3 = new User("user", pwEncoder.encode("test"), "kevin.leijnse@gmail.com", "Kevin", "Leijnse", "Leidijk 97", "06-18756892", "Groningen");
				user3.setRoles(Set.of("ROLE_USER"));

				usService.save(user1);
				usService.save(user2);
				usService.save(user3);
			}

			List<Book> initialBooks = List.of(
				new Book("The Pragmatic Programmer", "David Thomas, Andrew Hunt", 2006, 1),
				new Book("Clean Code", "Robert C. Martin", 2020, 2),
				new Book("Code Complete", "Steve McConnell", 2012, 1),
				new Book("Refactoring", "Martin Fowler", 2017, 4),
				new Book("Head First Design Patterns", "Eric Freeman, Bert Bates, Kathy Sierra, Elisabeth Robson", 2019, 5),
				new Book("The Mythical Man-Month", "Frederick P. Brooks Jr", 1999, 1),
				new Book("The Clean Coder", "Robert Martin", 2021, 3),
				new Book("Working Effectively with Legacy Code", "Micheal Feathers", 2015, 1),
				new Book("Design Patterns", "Erich Gamma, Richard Helm. Ralph Johnson, John Vlissides", 2019, 2),
				new Book("Cracking the Coding Interview", "Gayle Laakmann McDowell", 2018, 3),
				new Book("Rework", "Jason Fried, David Heinemeier Hansson", 2011, 1),
				new Book("Don't Make Me Think", "Steve Krug", 2005, 1),
				new Book("Code", "Charles Petzold", 2017, 1),
				new Book("Peopleware", "Tom DeMarco, Tim Lister", 2013, 3),
				new Book("Introduction to Algorithms", "Thomas H. Cormen", 2020, 2),
				new Book("Programming Pearls", "Jon Bently", 1998, 1),
				new Book("Patterns of Enterprice Application Architecture", "Martin Fowler", 2020, 2),
				new Book("Structure and Interpretation of Computer Programs", "Harold Abelson, Gerald Jay Sussman, Julie Sussman", 2013, 1),
				new Book("The Art of Computer Programming", "Donald E. Knuth", 2014, 4),
				new Book("Domain-Driven Design", "Eric Evans", 2010, 2),
				new Book("Coders at Work", "Peter Seibel", 2009, 1),
				new Book("Rapid Development", "Steve McConnell", 1995, 6),
				new Book("The Self-Taught Programmer", "Cory Althoff", 2021, 1),
				new Book("Algorithms", "Robert Sedgewick, Kevin Wayne", 2000, 3),
				new Book("Continuous Delivery", "Jez Humble, David Farley", 2003, 1)
			);

			for (Book book : initialBooks) {
				if (!bookService.bookExists(book.getTitle(), book.getAuthor(), book.getReleaseYear(), book.getEdition())) {
					bookService.save(book);
				}
			}

			// Assign 2 books to user3 for demonstration only if not already assigned
			User user3 = usService.findById(3L);
			List<Book> books = bookService.findAll();

			Book book1 = books.stream().filter(b -> b.getTitle().equalsIgnoreCase("The Pragmatic Programmer")).findFirst().orElse(null);
			Book book10 = books.stream().filter(b -> b.getTitle().equalsIgnoreCase("Cracking the Coding Interview")).findFirst().orElse(null);

			if (book1 != null && book1.getTheUser() == null) {
				book1.setTheUser(user3);
				book1.setReturnDate(LocalDate.of(2021, 5, 5));
				bookService.save(book1);
			}

			if (book10 != null && book10.getTheUser() == null) {
				book10.setTheUser(user3);
				book10.setReturnDate(LocalDate.of(2021, 5, 23));
				bookService.save(book10);
			}

			midAppRef.midnightApplicationRefresher();
		};
	}
}
