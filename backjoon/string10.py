import sys

order = int(sys.stdin.readline().rstrip())
result = order

for i in range(0, order):
    N = set()
    non_N = set()
    S = sys.stdin.readline().rstrip()
    memory = S[0]
    N.add(S[0])
    
    for j in range(1, len(S)):
        if memory != S[j] and S[j] in N:
            memory = S[j]
            N.add(S[j])
            non_N.add(S[j])
        elif memory != S[j] and S[j] not in N:
            memory = S[j]
            N.add(S[j])
        
    if len(non_N) > 0:
        result -= 1 
       
print(result)
            