import sys
# set을 사용해서 다른내용들을 저장
s = set()
# 10개의 데이터 받기
for item in range(0,10):
    target = int(sys.stdin.readline())
    s.add(target % 42)

print(s.__len__())
