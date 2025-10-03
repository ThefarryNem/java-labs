
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

// Заявка от квартиросъемщика
class Request {
	private String workType;
	private String scale;
	private LocalDateTime desiredTime;

	public Request(String workType, String scale, LocalDateTime desiredTime) {
		this.workType = workType;
		this.scale = scale;
		this.desiredTime = desiredTime;
	}

	public String getWorkType() {
		return workType;
	}

	public String getScale() {
		return scale;
	}

	public LocalDateTime getDesiredTime() {
		return desiredTime;
	}

	@Override
	public String toString() {
		return String.format("Работы - %s, Масштаб - %s, Время - %s",
				workType, scale, desiredTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
	}
}

// Бригада
class Team {
	private String id;
	private boolean busy;
	private Request currentRequest;

	public Team(String id) {
		this.id = id;
		this.busy = false;
	}

	public boolean isBusy() {
		return busy;
	}

	public void assignRequest(Request request) {
		this.currentRequest = request;
		this.busy = true;
	}

	public void completeWork() {
		this.currentRequest = null;
		this.busy = false;
	}

	public String getId() {
		return id;
	}

	public Request getCurrentRequest() {
		return currentRequest;
	}

	@Override
	public String toString() {
		if (busy) {
			return String.format("Бригада %s (занята: %s)", id, currentRequest);
		} else {
			return String.format("Бригада %s (свободна)", id);
		}
	}
}

// План работ
class WorkPlan {
	private List<Team> teams = new ArrayList<>();
	private Map<Team, Request> assignments = new HashMap<>();

	public void addTeam(Team team) {
		teams.add(team);
	}

	public List<Team> getTeams() {
		return Collections.unmodifiableList(teams);
	}

	public boolean assignRequestToTeam(Request request) {
		for (Team team : teams) {
			if (!team.isBusy()) {
				team.assignRequest(request);
				assignments.put(team, request);
				return true;
			}
		}
		return false;
	}

	public void completeWork(Team team) {
		if (team.isBusy()) {
			team.completeWork();
			assignments.remove(team);
		}
	}

	public void printStatus() {
		System.out.println("Статус бригад:");
		for (Team team : teams) {
			System.out.println(team);
		}
	}
}

// Диспетчер
class Dispatcher {
	private WorkPlan workPlan;

	public Dispatcher(WorkPlan workPlan) {
		this.workPlan = workPlan;
	}

	public void handleRequest(Request request) {
		System.out.println("Обработка заявки: " + request);
		boolean assigned = workPlan.assignRequestToTeam(request);
		if (assigned) {
			System.out.println("Заявка принята и назначена бригаде.");
		} else {
			System.out.println("Все бригады заняты, заявка отклонена.");
		}
	}
}

public class Main {
	private static final Scanner scanner = new Scanner(System.in);
	private static final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public static void main(String[] args) {
		WorkPlan workPlan = new WorkPlan();

		// Добавим 2 бригады
		workPlan.addTeam(new Team("A1"));
		workPlan.addTeam(new Team("B2"));

		Dispatcher dispatcher = new Dispatcher(workPlan);

		System.out.println("=== Система Жилищно-коммунальных услуг ===");
		System.out.println("Введите 'exit' в любом поле для выхода.");

		while (true) {
			try {
				System.out.print("\nВведите род работ: ");
				String workType = scanner.nextLine();
				if (workType.equalsIgnoreCase("exit"))
					break;

				System.out.print("Введите масштаб (мелкий/средний/капитальный): ");
				String scale = scanner.nextLine();
				if (scale.equalsIgnoreCase("exit"))
					break;
				if (!(scale.equalsIgnoreCase("мелкий")
						|| scale.equalsIgnoreCase("средний")
						|| scale.equalsIgnoreCase("капитальный"))) {
					System.out.println(
							"Ошибка: масштаб должен быть 'мелкий', 'средний' или 'капитальный'. Попробуйте снова.");
					continue;
				}

				System.out.print("Введите желаемое время (формат yyyy-MM-dd HH:mm): ");
				String timeStr = scanner.nextLine();
				if (timeStr.equalsIgnoreCase("exit"))
					break;
				LocalDateTime desiredTime;
				try {
					desiredTime = LocalDateTime.parse(timeStr, dtFormatter);
				} catch (DateTimeParseException e) {
					System.out.println("Ошибка формата даты/времени. Попробуйте снова.");
					continue;
				}

				Request request = new Request(workType, scale.toLowerCase(), desiredTime);
				dispatcher.handleRequest(request);
				workPlan.printStatus();

				System.out.println(
						"\nЕсли хотите завершить работу какой-либо бригады, введите ID бригады (например, A1), иначе нажмите Enter:");
				String teamId = scanner.nextLine();
				if (!teamId.trim().isEmpty()) {
					boolean completed = false;
					for (Team team : workPlan.getTeams()) {
						if (team.getId().equalsIgnoreCase(teamId.trim())) {
							workPlan.completeWork(team);
							System.out.printf("Работа бригады %s завершена.%n", team.getId());
							completed = true;
							break;
						}
					}
					if (!completed) {
						System.out.println("Бригада с таким ID не найдена.");
					}
					workPlan.printStatus();
				}

			} catch (Exception e) {
				// Общий обработчик на случай неожиданных ошибок
				System.out.println("Произошла ошибка: " + e.getMessage());
			}
		}

		System.out.println("Завершение работы системы. Спасибо!");
	}
}
