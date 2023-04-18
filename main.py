import threading
import time
from random import randint

num_philosophers = 5

def print_synchronized(message):
    with print_lock:
        print(message)

# Функція, яка імітує дії філософа
def philosopher(thread_id, left_fork_idx, right_fork_idx):
    left_fork = forks[left_fork_idx]
    right_fork = forks[right_fork_idx]

    while True:
        time.sleep(randint(1, 5))
        print_synchronized(f"Philosopher {thread_id} is thinking")

        if left_fork_idx < right_fork_idx:
            first_fork, second_fork = left_fork, right_fork
        else:
            first_fork, second_fork = right_fork, left_fork

        with first_fork:
            with second_fork:
                print_synchronized(f"Philosopher {thread_id} is eating")
                time.sleep(randint(1, 5))  # Філософ їсть

# Створюємо виделки (ресурси)
forks = [threading.Lock() for _ in range(num_philosophers)]

# Створюємо блокування для синхронізації виводу
print_lock = threading.Lock()

# Створюємо та запускаємо потоки філософів
philosopher_threads = []
for i in range(num_philosophers):
    t = threading.Thread(target=philosopher, args=(i, i, (i + 1) % num_philosophers))
    t.start()
    philosopher_threads.append(t)

# Чекаємо завершення потоків (ніколи не відбудеться)
for t in philosopher_threads:
    t.join()
