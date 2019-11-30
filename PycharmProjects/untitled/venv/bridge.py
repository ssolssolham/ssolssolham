bridge_length = 2
weight = 10
truck_weights = [7,4,5,6]
answer = 0

truck_wait = truck_weights[:]
truck_going = []
truck_went = []
# 트럭 여러 대가 일차선 다리를 정해진 순으로 건넘
# 모든 트럭이 다리를 건너려면 최소 몇초?
# 트럭은 1초에 1만큼 움직임

# 트럭의 거리 상태 초기화
truck_bridge_how = []

while len(truck_went) != len(truck_weights):

    # 다리보다 길게 갔으면 건너간것!
    #for item in temp_arr:
    if len(truck_bridge_how) != 0 and truck_bridge_how[0] == bridge_length:
        truck_went.append(truck_going.pop(0))

    # 새로운 트럭이 다리를 진입
    # 복수의 트럭은 진입 불가!
    if sum(truck_going) + truck_wait[0] < weight:
        temp = truck_wait.pop(0)
        truck_going.append(temp)
        truck_bridge_how.append(0)

    temp_arr = []

    # 1초마다 트럭이 다리를 건넘
    for index, item in enumerate(truck_bridge_how):
        truck_bridge_how[index] += 1
#        if truck_bridge_how[index] > bridge_length:
#            temp_arr.append(index)


    print("truck_wait : " + str(truck_wait))
    print("truck_bridge_how : " + str(truck_bridge_how))
    print("truck_going : " + str(truck_going))
    print("truck_went : " + str(truck_went))

    answer += 1
    print(answer)
print(answer)
    # 다리를 완전히 건넘





