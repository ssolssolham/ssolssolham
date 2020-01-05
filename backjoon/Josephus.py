import sys

class CircularQueue():
    def __init__(self, size):
        self.queue = [0 for x in range(size + 1)]
        self.front = 0
        self.rear = 0
        self.max_size = size
        self.list_size = size + 1
    
    def push(self, num):
        if (self.rear + 1) % self.list_size == self.front:
            return -1
        self.queue[self.rear] = num
        self.rear = (self.rear + 1) % self.list_size
    
    def pop(self):
        if self.size() == 0:
            return -1
        temp = self.queue[self.front]
        self.front = (self.front + 1) % self.list_size
        return temp
    
    def size(self):
        if self.front == self.rear:
            return 0
        elif self.rear > self.front:
            return self.rear - self.front
        else:
            return self.max_size - (self.front - self.rear) + 1
        
    def empty(self):
        return 1 if self.front == self.rear else 0
    
    def max(self):
        return max(self.queue)
    
    def print(self):
        print(self.queue)
        print(self.front)
        print(self.rear)

n, m = map(int, sys.stdin.readline().rstrip().split())

queue = CircularQueue(n)
    
output_str = "<"

for i in range(1, n+1):
    queue.push(i)

while(True):
    for i in range(m-1):
        queue.push(queue.pop())
    
    output_str = output_str + str(queue.pop())
    if queue.empty() == 1:
        break
    else:
        output_str = output_str + ", "

print(output_str + ">")