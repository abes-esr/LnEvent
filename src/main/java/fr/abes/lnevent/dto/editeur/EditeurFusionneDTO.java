package fr.abes.lnevent.dto.editeur;

import lombok.Getter;

import java.util.List;

@Getter
public class EditeurFusionneDTO {
    private EditeurDTO editeur;
    private List<String> idEditeurFusionnes;
}
