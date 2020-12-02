package layer.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import layer.dao.Dao;
import layer.dao.FuncionarioDao;
import layer.teste.Funcionario;

public class FluxoThread implements Runnable {

	private String nomeArquivo;
	private String arquivoConfig;
	public  FuncionarioDao funcionarioDao;
	public String tempoExecucao;
	
	
	public FluxoThread(String nomeArquivo, FuncionarioDao funcionarioDao){
		this.nomeArquivo = nomeArquivo;
		this.funcionarioDao = funcionarioDao;
	}
	
	@Override
	public void run() {
//		for(int i=0;i<10000;i++) {
//			System.out.println(i + ":"+nomeArquivo);
//		}
		long inicio = System.currentTimeMillis();
		String arquivo = "C:/Users/vitor/Desktop/desafio/challenge-eventstore-master/"+nomeArquivo+".txt";
//		String arquivo = "C:/DUP_ABRAMUS_20201119.txt";
		FileInputStream fr;
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
//		List<String> listaLinhas= new ArrayList<String>();
		
		try {
			fr = new FileInputStream(arquivo);
			
			Scanner scanner = new Scanner(fr);
			String texto = scanner.toString();
			String[] textoArray;
			
			while(scanner.hasNextLine()) {
				Funcionario fn = new Funcionario(); 
				texto = scanner.nextLine();
				 textoArray = texto.split(" ");
				 fn.setNome(textoArray[0]);
				 fn.setIdade(Long.valueOf(textoArray[1]));
				 fn.setDescricao(textoArray[2]);
				 listaFuncionario.add(fn);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
				
			Session session = Dao.factory.openSession();
//			Session session = funcionarioDao.factory.getCurrentSession();
			Transaction trans = session.beginTransaction();
					
			for(Funcionario f : listaFuncionario) {
//			System.out.println(f +":"+ nomeArquivo);
//			funcionarioDao.salvar(f);
			session.persist(f);
			}
			
			trans.commit();
			System.out.println(System.currentTimeMillis() - inicio+":"+nomeArquivo);
			tempoExecucao = System.currentTimeMillis() - inicio+":"+nomeArquivo;
			
			System.out.println(funcionarioDao.findAll().size());
		}	
	}

	public void valoresProp() throws IOException{
		Properties prop = new Properties();
		InputStream is = new FileInputStream(new File("C:/prop.txt"));
		prop.load(is);
		System.out.println(prop.getProperty("nome"));
	}
}
