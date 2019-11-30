str = "Let's take LeetCode contest"
result = ""
for item in str.split(' '):
    result += item[::-1] + " "
result = result[:-1]

print(result)