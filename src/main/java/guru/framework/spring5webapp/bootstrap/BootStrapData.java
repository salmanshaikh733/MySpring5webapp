package guru.framework.spring5webapp.bootstrap;

import guru.framework.spring5webapp.domain.Author;
import guru.framework.spring5webapp.domain.Book;
import guru.framework.spring5webapp.domain.Publisher;
import guru.framework.spring5webapp.repositories.AuthorRepository;
import guru.framework.spring5webapp.repositories.BookRepository;
import guru.framework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 12/23/19.
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository=publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);


        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        //we add a publisher
        Publisher newPub = new Publisher();
        newPub.setAddressLine1("123 AnyStreet");
        newPub.setCity("New City");
        newPub.setName("publisher");
        newPub.setState("newState");
        newPub.setZip("12345");


        ddd.setPublisher(newPub);
        newPub.getBooks().add(ddd);
        publisherRepository.save(newPub);

        noEJB.setPublisher(newPub);
        newPub.getBooks().add(noEJB);
        publisherRepository.save(newPub);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: "+ newPub.getBooks().size());
        System.out.println("Number of publishers: " + publisherRepository.count());

    }


}