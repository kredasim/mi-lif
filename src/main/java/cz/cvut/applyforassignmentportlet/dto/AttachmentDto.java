/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.applyforassignmentportlet.dto;

import org.primefaces.model.StreamedContent;

/**
 *
 * @author simo
 */
public class AttachmentDto {
    
    private String comment;
    private StreamedContent file;

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }    
}
