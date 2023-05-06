import socket
import threading
import time

def f(x):
    return x * 2

def g(x):
    return x + 3

def server_function(sock, func, x):
    conn, _ = sock.accept()
    result = func(x)
    conn.sendall(str(result).encode())
    conn.close()

def client_function(sock, addr):
    sock.connect(addr)
    result = sock.recv(1024).decode()
    sock.close()
    return result

def main():
    x = int(input("Введіть значення x: "))

    sock_f = socket.socket()
    sock_f.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    sock_f.bind(("", 8084))
    sock_f.listen(1)
    server_f = threading.Thread(target=server_function, args=(sock_f, f, x))
    server_f.start()

    sock_g = socket.socket()
    sock_g.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    sock_g.bind(("", 8085))
    sock_g.listen(1)
    server_g = threading.Thread(target=server_function, args=(sock_g, g, x))
    server_g.start()

    timeout = 10
    check_interval = 1
    elapsed_time = 0
    completed = [False, False]
    results = [None, None]

    while not all(completed) and elapsed_time < timeout:
        if not completed[0]:
            try:
                sock_f_client = socket.socket()
                results[0] = int(client_function(sock_f_client, ("localhost", 8084)))
                completed[0] = True
            except socket.error:
                pass
        if not completed[1]:
            try:
                sock_g_client = socket.socket()
                results[1] = int(client_function(sock_g_client, ("localhost", 8085)))
                completed[1] = True
            except socket.error:
                pass

        if not all(completed):
            time.sleep(check_interval)
            elapsed_time += check_interval

    if all(completed):
        result = min(results[0], results[1])
    else:
        while True:
            choice = int(input("1) продовжити обчислення, 2) припинити, 3) продовжити, не перепитуючи більше: "))
            if choice == 1:
                timeout += 10
                while not all(completed) and elapsed_time < timeout:
                    if not completed[0]:
                        try:
                            sock_f_client = socket.socket()
                            results[0] = int(client_function(sock_f_client, ("localhost", 8084)))
                            completed[0] = True
                        except socket.error:
                            pass

                    if not completed[1]:
                        try:
                            sock_g_client = socket.socket()
                            results[1] = int(client_function(sock_g_client, ("localhost", 8085)))
                            completed[1] = True
                        except socket.error:
                            pass

                    if not all(completed):
                        time.sleep(check_interval)
                        elapsed_time += check_interval

                if all(completed):
                    result = min(results[0], results[1])
                    break
                else:
                    continue
            elif choice == 2:
                result = "невизначено"
                break
            elif choice == 3:
                timeout += 10
                continue
            else:
                print("Некоректний вибір. Введіть 1, 2 або 3.")

    print("f(x) && g(x) =", result)

    sock_f.close()
    sock_g.close()
    server_f.join()
    server_g.join()

if __name__ == "__main__":
    main()

