N = int(input())
for i in range(1, N+1):
    diff = N - i
    star = '*' * i
    print((diff * ' ') + star)