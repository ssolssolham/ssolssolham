import sys
N = sys.stdin.readline().rstrip()
cnt = 1
target = int(N)

while True :
   ten = int(N) // 10
   one = int(N) % 10
   new = ten + one
   N = str(one) + str(new % 10)    
   
   if int(N) == target:
       break
   else:
       cnt += 1
       
print(cnt)        
