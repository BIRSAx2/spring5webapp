package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository= publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in BootStrap");

        Publisher feltrinelliEditore = new Publisher();
        feltrinelliEditore.setName("Feltrinelli Editore");
        feltrinelliEditore.setCity("Milan");
        feltrinelliEditore.setState("Italy");

        publisherRepository.save(feltrinelliEditore);


        Author harukiMurakami = new Author("Haruki","Murakami");
        Book norwegianWood = new Book("Norwegian Wood","1234567890");

        harukiMurakami.getBooks().add(norwegianWood);
        norwegianWood.getAuthors().add(harukiMurakami);
        norwegianWood.setPublisher(feltrinelliEditore);
        feltrinelliEditore.getBooks().add(norwegianWood);

        authorRepository.save(harukiMurakami);
        bookRepository.save(norwegianWood);
        publisherRepository.save(feltrinelliEditore);


        Author kenFollet = new Author("Ken","Follet");
        Book fallOfGiants = new Book("Fall Of Giants","0987654321");

        kenFollet.getBooks().add(fallOfGiants);
        fallOfGiants.getAuthors().add(kenFollet);
        fallOfGiants.setPublisher(feltrinelliEditore);
        feltrinelliEditore.getBooks().add(fallOfGiants);

        authorRepository.save(kenFollet);
        bookRepository.save(fallOfGiants);
        publisherRepository.save(feltrinelliEditore);

        System.out.println("Number of Publisher: "+publisherRepository.count());
        System.out.println("Number of Books: "+bookRepository.count());
        System.out.println("Publisher Number of Books: "+feltrinelliEditore.getBooks().size());
    }
}
