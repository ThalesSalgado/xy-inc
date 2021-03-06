package br.com.thales.xyinc.dto;

import br.com.thales.xyinc.model.Poi;
import br.com.thales.xyinc.service.validation.PoiInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@PoiInsert
public class PoiDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=80, message="O nome deve ter entre 5 e 80 caracteres")
    private String nome;

    @Min(value=0, message="O valor precisa ser positivo")
    private Integer x;

    @Min(value=0, message="O valor precisa ser positivo")
    private Integer y;

    public PoiDTO() {
    }

    public PoiDTO(Poi obj) {
        id = obj.getId();
        nome = obj.getNome();
        x = obj.getX();
        y = obj.getY();
    }

    public PoiDTO(Integer id, String nome, Integer x, Integer y) {
        this.id = id;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
