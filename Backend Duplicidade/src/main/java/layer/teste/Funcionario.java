package layer.teste;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tbFuncionario")
public class Funcionario {
	@Id @Column(name="id") @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "nome", nullable = false)
	private String nome;
	@Column(name = "idade", nullable = false)
	private long idade;
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getIdade() {
		return idade;
	}
	public void setIdade(long idade) {
		this.idade = idade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<Funcionario> cargaFuncionario() throws Exception{
		String arquivo = "C:/teste.txt";
		FileInputStream fr;
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		
		try {
			fr = new FileInputStream(arquivo);
			
			Scanner scanner = new Scanner(fr);
			String texto = scanner.toString();
			String[] textoArray;
			
			while(scanner.hasNextLine()) {
				Funcionario fn = new Funcionario(); 
				texto = scanner.nextLine();
				 textoArray = texto.split(" ");
				 fn.nome = textoArray[0];
				 fn.idade = Long.valueOf(textoArray[1]);
				 fn.descricao = textoArray[2];
				 listaFuncionario.add(fn);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			return listaFuncionario;
		}
	}
	
	public void dividirLinhas() throws IOException {
		List<String> list = new ArrayList<String>();
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();
		BufferedOutputStream bw1 = new BufferedOutputStream(new FileOutputStream("split1.txt"));
		BufferedOutputStream bw2 = new BufferedOutputStream(new FileOutputStream("split2.txt"));
		BufferedOutputStream bw3 = new BufferedOutputStream(new FileOutputStream("split3.txt"));
		BufferedOutputStream bw4 = new BufferedOutputStream(new FileOutputStream("split4.txt"));
	    Funcionario f1 = new Funcionario();
	    int destIx = 0;
	    File file = new File("C:/teste.txt");
	    if(file.exists()){
	        try { 
	            list = Files.readAllLines(file.toPath(),Charset.defaultCharset());
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	      if(list.isEmpty())
	          return ;
	    }
//	    for(String line : list){
//	    	destIx++;
//	    	BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("split"+destIx+".txt"));
//	    	String[] res = line.split(" ");
//	        f1.nome = res[0];
//	        f1.idade = Long.valueOf(res[1]);
//	        f1.descricao = res[2];
//	        bw.write(f1.toString().getBytes());
//	        bw.close();
//	    }
	    for(int i = 0; i<list.size(); i++) {
	    	if(i%4==0 || i == 0) {
	    		list1.add(list.get(i));
	    		continue;
	    	}
	    	if(i%4==1 || i == 1) {
	    		list2.add(list.get(i));
	    		continue;
	    	}
			if(i%4==2 || i == 2) {
				list3.add(list.get(i));
				continue;
	    	}
			if(i%4==3 || i == 3) {
				list4.add(list.get(i));
	    	}
	    }
	    varrerLista(list1,bw1);
	    varrerLista(list2,bw2);
	    varrerLista(list3,bw3);
	    varrerLista(list4,bw4);
	} 
	
	public void varrerLista(List<String> lista, BufferedOutputStream bw) throws IOException {
		for(String line : lista){
	    	line += "\n";
			bw.write(line.getBytes());
	    }
		bw.close();
	}
	
	@Override
	public String toString() {
		return "nome: "+ this.getNome() +" idade: "+ this.getIdade() +" descricao: "+ this.getDescricao();
	}
	
	
//	public void quebrarArquivo() throws IOException {
//	String arquivo = "C:/teste.txt";
//	RandomAccessFile raf = new RandomAccessFile(arquivo,"r");
//	long numSplits = 4;
//	long sourceSize = raf.length();
//	long bytesPerSplit = sourceSize/numSplits ;
//    long remainingBytes = sourceSize % numSplits;
//    
//    int maxReadBufferSize = 8 * 1024; //8KB
//    for(int destIx=1; destIx <= numSplits; destIx++) {
//        BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("split"+destIx+".txt"));
//        if(bytesPerSplit > maxReadBufferSize) {
//            long numReads = bytesPerSplit/maxReadBufferSize;
//            long numRemainingRead = bytesPerSplit % maxReadBufferSize;
//            for(int i=0; i<numReads; i++) {
//                readWrite(raf, bw, maxReadBufferSize);
//            }
//            if(numRemainingRead > 0) {
//                readWrite(raf, bw, numRemainingRead);
//            }
//        }else {
//            readWrite(raf, bw, bytesPerSplit);
//        }
//        bw.close();
//    }
//    if(remainingBytes > 0) {
//        BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("split"+(numSplits+1)+".txt"));
//        readWrite(raf, bw, remainingBytes);
//        bw.close();
//    }
//        raf.close();
//}
//static void readWrite(RandomAccessFile raf, BufferedOutputStream bw, long numBytes) throws IOException {
//    byte[] buf = new byte[(int) numBytes];
//    int val = raf.read(buf);
//    if(val != -1) {
//        bw.write(buf);
//    }
//}
}
