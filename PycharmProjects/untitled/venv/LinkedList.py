def __init__(self):
    self.nodeCount = 0
    self.head = None
    self.tail = None

def getAt(self, pos):
    if pos < 1 or pos > self.nodeCount:
        return None
    i = 1
    curr = self.head
    while i < pos:
        curr = curr.next
        i += 1
    return curr

def insertAt(self, pos, newNode):
    if pos < 1 or pos > self.nodeCount + 1:
        return False

    if pos == 1:
        newNode.next = self.head
        self.head = newNode

    else:
        if pos == self.nodeCount + 1:
            prev = self.tail
        else:
            prev = self.getAt(pos - 1)
        newNode.next = prev.next
        prev.next = newNode

    if pos == self.nodeCount + 1:
        self.tail = newNode

    self.nodeCount += 1
    return True

def popAt(self, pos):
    if pos < 1 or pos > self.nodeCount:
        raise IndexError
    elif self.nodeCount == 1 and pos == 1:
        data = self.head.data
        self.head = None
        self.tail = None
    else:
        prev = self.getAt(pos - 1)
        data = prev.next.data
        if pos == self.nodeCount:
            self.tail = prev
            prev.next = None
        else:
            prev.next = prev.next.next
    self.nodeCount -= 1
    return data


def traverse(self):
    answer = []
    curr = self.head
    while curr != None:
        answer.append(curr.data)
        curr = curr.next
    return answer