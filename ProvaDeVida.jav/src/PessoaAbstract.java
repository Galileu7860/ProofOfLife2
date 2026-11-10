
public class PessoaAbstract {

public abstract class Pessoa {
    private String nome;
    private int idade;
    private String cpf;
	
    public void fazerAniversário(int i) {
    	this.idade ++;
    }
    
    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getcpf() {
		return cpf;
	}
	public void setcpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", idade=" + idade + ", cpf=" + cpf + ", getNome()=" + getNome()
				+ ", getIdade()=" + getIdade() + ", getcpf()=" + getcpf() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}

}

