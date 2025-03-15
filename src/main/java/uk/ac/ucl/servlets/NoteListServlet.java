package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.model.NoteRecord;
import uk.ac.ucl.model.NoteSorter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.Objects;


@WebServlet("/notes_list")
public class NoteListServlet extends AbstractJSPServlet
{
 
    private final Model model;
    
    public NoteListServlet() throws IOException
    {
         model = ModelFactory.getModel();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException, IllegalArgumentException
  {
    LinkedHashMap<LocalDateTime, NoteRecord> noteMap = model.getNoteData();
    
    String sortChoice = Objects.requireNonNullElse(request.getParameter("sort"), "mostRecent");
    
    List<Map.Entry<LocalDateTime, NoteRecord>> noteList = switch (sortChoice)
    {
        case "leastRecent" -> NoteSorter.leastRecent(noteMap);
        case "alphabetical" -> NoteSorter.alphabetical(noteMap);
        case "revAlphabetical" -> NoteSorter.revAlphabetical(noteMap);
        case "mostRecent" -> NoteSorter.mostRecent(noteMap);
        default -> throw new IllegalArgumentException("Invalid sort choice");
    };
    
    request.setAttribute("noteList", noteList);

    invokeJSP("/notesList.jsp", request, response);
  }
}
