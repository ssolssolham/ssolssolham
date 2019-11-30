n = 1000
a = [False,False] + [True]*(n-1)
primes = []

# index 보정을 위해 n+1까지 범위 정의
# 시작점이 2인 이유는 0부터 시작하기에..
# 0,1은 False로 시작

for i in range(2,n+1):
    # debug : i = 2, primes.append(2)
    # primes = [2]
    # for j in range(4, 1001, 2)
    # 2의배수를 제거한다.
    # a 배열의 2의배수는 if문에서 False로 바뀌어 작동 x
    if a[i]:
        primes.append(i)
        for j in range(2*i, n+1, i):
            a[j] = False
print(primes)