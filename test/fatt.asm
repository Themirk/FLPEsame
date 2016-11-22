.def f: args=1 ,locals=3
store 0
load 0
iconst 0
ieq
brf loop1
iconst 1
ret
br loop2
loop1:
load 0
iconst 1
isub
call f()
load 0
imul
ret
loop2:
.def main: args=0 ,locals=0
iconst 4
call f()
print
halt
