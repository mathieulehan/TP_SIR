package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ResponseQuestion {
	
	
		@Id @GeneratedValue
		long id;

		Double note;

		@ManyToOne
		Candidat c;

		@ManyToOne
		Questionnaire q;
		
		@OneToMany(mappedBy="reponsequestion")
		Collection<Reponse> reponses;
		
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public Candidat getC() {
			return c;
		}
		public void setC(Candidat c) {
			this.c = c;
		}
		public Questionnaire getQ() {
			return q;
		}
		public void setQ(Questionnaire q) {
			this.q = q;
		}
		public Collection<Reponse> getReponses() {
			return reponses;
		}
		public void setReponses(Collection<Reponse> reponses) {
			this.reponses = reponses;
		}
		public Double getNote() {
			return note;
		}
		public void setNote(Double note) {
			this.note = note;
		}
		
	
}
