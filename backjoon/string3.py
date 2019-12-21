import sys
S = sys.stdin.readline().rstrip()

num = 97
alphaIndexList = [-1 for c in range(ord('a'), ord('z')+1)]
i = 0

for item in list(S):
    target = ord(item) - 97
    if alphaIndexList[target] == -1:
        alphaIndexList[target] = i
    i += 1

result = str(alphaIndexList[0])
for j in range(1,26):
    result += " " + str(alphaIndexList[j])

print(result)    
#for item in list(S):
#    item
#print(len(alphaList))
#print(len(alphaIndexList))
