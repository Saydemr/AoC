
elves = []

with open('input.txt', 'r') as f:
    elf = 0
    for line in f:
        if len(line.strip()) != 0:
            elf += int(line.strip())
        else:
            elves.append(elf)
            elf = 0
elves.sort()
print(elves[-1] + elves[-2] + elves[-3])
