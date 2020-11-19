package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author harukiMurakami = new Author("Haruki","Murakami");
        Book norwegianWood = new Book("Norwegian Wood","1234567890");

        harukiMurakami.getBooks().add(norwegianWood);
        norwegianWood.getAuthors().add(harukiMurakami);

        Author kenFollet = new Author("Ken","Follet");
        Book fallOfGiants = new Book("Fall Of Giants","0987654321");

        kenFollet.getBooks().add(fallOfGiants);
        fallOfGiants.getAuthors().add(kenFollet);


        authorRepository.save(harukiMurakami);
        authorRepository.save(kenFollet);
        bookRepository.save(norwegianWood);
        bookRepository.save(fallOfGiants);

        System.out.println("Started in BootStrap");
        System.out.println("Number of Books: "+bookRepository.count());
    }
}
