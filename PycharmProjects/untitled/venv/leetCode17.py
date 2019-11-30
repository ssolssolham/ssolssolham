L = 6
R = 10

def primes_up_to_good(n:int) -> [int]:
    seive = [False, False] + [True] * (n - 1)
    for k in range(2, int(n ** 0.5 + 1.5)):
        if seive[k]:
            seive[k*2::k] = [False] * ((n - k) // k)
    return [x for x in range(n+1) if seive[x]]

for item in range(L,R + 1):
    cnt = format(item, 'b').count('1')
    if primes_up_to_good(cnt) == 1:
        answer += 1

    print(format(item, 'b'))
    print(format(item, 'b').count('1'))
    print()


