import sys

S = sys.stdin.readline().rstrip()

small_a_num = 97
big_A_num = 65
big_Z_num = 90

alphaIndexList = [0 for c in range(ord('a'), ord('z') + 1)]
for item in list(S):
    if ord(item) > big_Z_num:
        alphaIndexList[ord(item) - small_a_num] += 1
    else :
        alphaIndexList[ord(item) - big_A_num] += 1

max = -1
tf = False        
word = ''

for index,item in enumerate(alphaIndexList):
    if item != 0 and item > max:
        max = item
        word = chr(index + big_A_num)
        tf = True
        
    elif item != 0 and item == max:
        tf = False

if tf:
    print(word)
else:
    print('?')
        

