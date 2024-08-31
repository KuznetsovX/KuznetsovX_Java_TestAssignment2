import com.kuznetsov.assignment2.DocumentManager;

import java.util.List;
import java.util.Optional;

public class DocumentManagerTest {
    public static void main(String[] args) {
        DocumentManager documentManager = new DocumentManager();


        // Authors -->
        DocumentManager.Author author1 = DocumentManager.Author.builder()
                .id("1_sb")
                .name("Sponge Bob")
                .build();

        DocumentManager.Author author2 = DocumentManager.Author.builder()
                .id("2_ps")
                .name("Patrick Star")
                .build();


        // Documents -->
        DocumentManager.Document document1 = DocumentManager.Document.builder()
                .title("Introduction to Java")
                .content("Java is a programming language. Good luck with it :D! No refunds.")
                .author(author1)
                .build();

        DocumentManager.Document document2 = DocumentManager.Document.builder()
                .title("Java in-depth")
                .content("Unless you live under a rock, you won't be able to understand this book.")
                .author(author2)
                .build();


        // Save -->
        DocumentManager.Document savedDocument1 = documentManager.save(document1);
        DocumentManager.Document savedDocument2 = documentManager.save(document2);


        // Print -->
        System.out.println("Saved Document #1: " + savedDocument1);
        System.out.println("Saved Document #2: " + savedDocument2);


        // Search by title prefix -->
        DocumentManager.SearchRequest searchRequest1 = DocumentManager.SearchRequest.builder()
                .titlePrefixes(List.of("Introduction"))
                .build();
        List<DocumentManager.Document> searchResults1 = documentManager.search(searchRequest1);
        System.out.println("Search results by title prefix \"Introduction\": " + searchResults1);


        // Search by content -->
        DocumentManager.SearchRequest searchRequest2 = DocumentManager.SearchRequest.builder()
                .containsContents(List.of("in-depth"))
                .build();
        List<DocumentManager.Document> searchResults2 = documentManager.search(searchRequest2);
        System.out.println("Search results by content \"in-depth\": " + searchResults2);


        // Find by ID -->
        Optional<DocumentManager.Document> foundDocument = documentManager.findById(savedDocument1.getId());
        foundDocument.ifPresentOrElse(
                document -> System.out.println("Found by ID: " + document),
                () -> System.out.println("Document not found.")
        );


        // Find by author ID -->
        DocumentManager.SearchRequest searchRequest3 = DocumentManager.SearchRequest.builder()
                .authorIds(List.of("author2"))
                .build();
        List<DocumentManager.Document> searchResults3 = documentManager.search(searchRequest3);
        System.out.println("Search Results by Author ID 'author2': " + searchResults3);
    }
}
