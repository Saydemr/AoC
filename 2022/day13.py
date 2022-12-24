import copy
from functools import cmp_to_key
import numpy as np


def step(a, b):
    if type(a) == int and type(b) == int:
        return np.sign(a - b)
    elif type(a) == list and type(b) == int:
        return step(a, [b])
    elif type(a) == int and type(b) == list:
        return step([a], b)
    elif a == [] and b == []:
        return 0
    elif a == [] and b != []:
        return -1
    elif a != [] and b == []:
        return 1
    else:
        return step(a[0], b[0]) or step(a[1:], b[1:])


count = 0
index = 1
with open('inputs/input13.txt', 'r') as f:
    lines = f.readlines()

    lines = [x.strip() for x in lines if x.strip() != '']
    part2_lines = copy.deepcopy(lines)

    # chunk the lines into groups of 2
    lines = [lines[i:i + 2] for i in range(0, len(lines), 2)]

    for left_expression, right_expression in lines:
        left = eval(left_expression)
        right = eval(right_expression)
        if step(left, right) < 0:
            count += index

        index += 1

    print(count)
    # part 2

    part2_lines.append('[[2]]')
    part2_lines.append('[[6]]')

    part2_lines = [eval(x) for x in part2_lines]

    # sort part2_lines based on the step
    part2_lines = sorted(part2_lines, key=cmp_to_key(step))
    result = (part2_lines.index([[2]]) + 1) * (part2_lines.index([[6]]) + 1)

    print(result)
