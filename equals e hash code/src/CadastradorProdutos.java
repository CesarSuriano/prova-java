import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class CadastradorProdutos {
	public static void main(String[] args) {
		Collection<Produto> produtos = new HashSet<>();
		
		System.out.println("########### Cadastro de produtos ############\n");
		
		try (Scanner entrada = new Scanner(System.in)){
			String continuar = "s";
			while("s".equalsIgnoreCase(continuar)) {
				System.out.println("SKU: ");
				String sku = entrada.nextLine();
				
				System.out.println("Nome: ");
				String nome = entrada.nextLine();
				
				Produto produto = new Produto(sku, nome);
				if(produtos.contains(produto)) {
					System.err.println("Esse produto já foi adicionado, utilize outro SKU");
				} else {
					produtos.add(produto);
					System.out.println("Produto adicionado");
				}
				
				System.out.println("Deseja adicionar mais algum produto? (s/n)");
				
				continuar = entrada.nextLine();
			}
		} 
		produtos.forEach(System.out::println);
		
		System.out.println("Foi");
	}
}
