import re
skill = "CBD"
skill = list(skill)

result = 0
# print(skill)

skill_trees = ["BACDE", "CBADF", "AECB", "BDA"]
arr = []

print('c' in ['c','b'])

for item in skill_trees:
    target = list(item)
    str = ""
    for it in target:
        if it in skill:
            str += it
            print(it)
    arr.append(str)
print(arr)


for item in arr:
    if item == '':
        print(item)
    for num,tar in enumerate(list(item)):
        if tar != skill[num]:
            break;
        if (len(list(item)) - 1) == num and tar == skill[num]:
            result += 1

print(result)


#print(arr)



#for skill 
#print(skill[0])
#print(skill[0] in skill_trees)
# print(skill_trees)
# print(skill_trees[0])
# **
