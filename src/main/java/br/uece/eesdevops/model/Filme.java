package br.uece.eesdevops.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Filme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String nomeProdutor;
	private String nomeAtor;
	private String anoLancamento;
	
	@OneToMany(mappedBy = "filme", cascade = CascadeType.ALL)
	private List<Avaliacao> avaliacoes;
	
	public double mediaAvaliacoes() {   			
    
		double notaAvaliacao = 0;
		int qtdAvaliacoes = avaliacoes.size();	
	    
	    for (Avaliacao avaliacao : avaliacoes) {
	      
	        notaAvaliacao += avaliacao.getNotaAvaliacao();	    
	    }
      
	    return notaAvaliacao/qtdAvaliacoes;
		 
	}

}
