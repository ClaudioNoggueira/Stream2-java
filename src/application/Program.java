package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;
import services.EmployeeService;

public class Program {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		List<Employee> employees = new ArrayList<>();
		try {
			System.out.println("Entre com o caminho do arquivo:");
			String path = input.nextLine();
			System.out.print("Entre com o salário: R$ ");
			double salary = input.nextDouble();

			try (BufferedReader br = new BufferedReader(new FileReader(path))) {
				String line = br.readLine();
				while (line != null) {
					String[] values = line.split(",");
					employees.add(new Employee(values[0], values[1], Double.parseDouble(values[2])));
					line = br.readLine();
				}

				System.out.println(
						"\nEmails dos funcionários com salário maior que R$ " + String.format("%.2f", salary) + ":");
				Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
				List<String> emails = employees.stream().filter(e -> e.getSalary() > salary).map(e -> e.getEmail())
						.sorted(comp).collect(Collectors.toList());
				emails.forEach(System.out::println);

				// double sum = employees.stream().filter(x -> x.getName().charAt(0) ==
				// 'M').map(x -> x.getSalary()).reduce(0.0, (x, y) -> x + y);
				double sum = EmployeeService.filteredSum(employees, p -> p.getName().charAt(0) == 'M');
				System.out.print("\nSoma dos salários das pessoas com nomes começando com 'M': R$ "
						+ String.format("%.2f", sum));

			} catch (IOException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		} catch (InputMismatchException e) {
			System.out.println("\nErro de formato. Foi inserido um valor inadequado para a operação.");
			System.out.println("Por favor, reinicie o programa e tente novamente.");
		} finally {
			if (input != null)
				input.close();
		}
	}
}
