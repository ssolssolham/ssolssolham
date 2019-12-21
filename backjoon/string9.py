import sys
N = ['c=','c-','dz=','d-','lj','nj','s=','z=']
target = sys.stdin.readline().rstrip()
result = 0

for index,item in enumerate(N):
    cnt = target.count(item)
    result += cnt
    target = target.replace(item,'1',cnt)    

target = target.replace('1','',target.count('1'))
result += len(target)
print(result)
    

