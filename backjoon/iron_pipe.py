import sys

class Stack(list):
    def __init__(self):
        self.stack_items = []
        self.stack_size = 0
    
    def push(self, data):
        self.stack_items.append(data)
        self.stack_size += 1
        
    def pop(self):
        if self.is_empty():
            return None
        self.stack_size -= 1
        return self.stack_items.pop()
    
    def peek(self):
        if self.is_empty():
            return None
        else : 
            return self.stack_items[self.is_size() - 1]
    
    def is_empty(self):
        if len(self.stack_items) == 0:
            return True
        return False
    
    def is_size(self):
        return self.stack_size

pipes = sys.stdin.readline().rstrip()
MyStack = Stack()
result = 0
before = ''
for item in list(pipes):
    if item == '(':
        MyStack.push(item)
    else : 
        MyStack.pop()
        
        if before == '(':
            result += MyStack.is_size()
        else :
            result += 1
    before = item

print(result)
        
