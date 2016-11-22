.def main: args=0 , locals=0
iconst 5
call perdueancora()
print
.def perdue: args=2 , locals=0
imul
ret
.def perdueancora: args=1 , locals=0
iconst 2
call perdue()
ret
