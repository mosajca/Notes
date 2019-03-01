package notes.model.note;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NoteForm {

    @NotNull
    @Size(max = 255)
    private String title;
    @Size(max = 2000)
    private String content;

    public NoteForm() {
    }

    public NoteForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
