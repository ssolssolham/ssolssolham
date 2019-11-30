#ipt = [2,1,3,2]
# [2,1,2]
# [2,2,1]
priorities = [1,1,9,1,1,1]
location = 0
location_ipt = priorities[location]
answer = 0
    # 가장 중요한 내역을 먼저 찾는다.
    # 중요한 내역을 인쇄(배열에서 제거) 후
    # 그 다음 중요한 내역을 찾는다.
    # 값이 동일한 경우.. 가장 중요한 내역과 최대한 가까운 내역을 인쇄

while len(priorities) != 0:
    # 가장 앞에 있는 문서를 대기목록에서 꺼냄
    printer_idx = 0
    pre_doc = priorities[0]
    tf = False

        # 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면
        # J를 대기목록의 가장 마지막에 넣습니다.
    for idx,item in enumerate(priorities):
        if pre_doc < item:
            priorities.append(pre_doc)
            priorities.pop(0)
            location -= 1
            tf = True
            break

    print(priorities)
    if tf:
        if location < 0:
            location = len(priorities) - 1
        continue

    # 그렇지 않으면 J를 인쇄합니다.

    print("location : " + str(location))
    print("ipts : " + str(priorities))
    if location == 0:
        answer += 1
        priorities.pop(0)
        break
    else:
        location -= 1
        answer += 1
        priorities.pop(0)

print("answer : " + str(answer))

    #print(ipt)
    #print(answer)


