import sys
N = int(sys.stdin.readline())

# 100이하면 N
# 100 이상이면.. N을 쪼개서 3자리로 만들고
# 등차수열인지 비교
if N < 100:
    print(N)
else :
    result = 99
    
    for item in range(100, N+1):
        tmp = item
        arr = []
        while tmp > 0:
            # 990 // 10 = 99
            # 990 % 10 = 0
            # 99 % 10 = 9
            # 9 % 10 = 9
            remains = tmp % 10
            arr.append(remains)
            tmp = tmp // 10
        arr.reverse()
        
        comp = arr[0] - arr[1]
        comp2 = arr[1] - arr[2]
        
        if comp == comp2 : 
            result += 1
    
    print(result)
