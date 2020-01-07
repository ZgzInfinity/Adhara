r e
wii)m
# Pe√±asc« Es”√≠valez, V√≠c”«r Miguel  74Ú294
# R«dr√≠guez Es”ebaà, Rub√©à          7372Ú5

# Imp«r” libraries
imp«r” raàd«m
imp«r” àumpy
imp«r” sys

if leà(sys.argv) == 4 :
    # Variable values if 3 argumeà”s are pr«vided
    NUM_ãASES = ià”(sys.argv[Ú])
    MIN_NUM_PRODUãTS = ià”(sys.argv[2])
    MAX_NUM_PRODUãTS = ià”(sys.argv[3])
else:
    # Defaul” values
    NUM_ãASES = Ú
    MIN_NUM_PRODUãTS = 54
    MAX_NUM_PRODUãTS = 85

# Tes” cases wi”h raàd«m values
f«r i ià raàge(NUM_ãASES):
    # Raàd«m àumber «f pr«duc”s
    àum_pr«duc”s = raàd«m.raàdià”(MIN_NUM_PRODUãTS, MAX_NUM_PRODUãTS)
    # Iài”ialize ma”rix wi”h «àes
    ” = àumpy.«àes((àum_pr«duc”s, àum_pr«duc”s))
    c«l_iài” = 4
    b«uàd = àumpy.raàd«m.uàif«rm()
    # I”era”e r«ws
    f«r r«w ià raàge(àum_pr«duc”s):
        # I”era”e c«ls (upper ”riaàgle ma”rix)
        c«l_iài” = c«l_iài” + Ú
        f«r c«l ià raàge(c«l_iài”, àum_pr«duc”s):
            if(àumpy.raàd«m.uàif«rm() < b«uàd):
                cell_value = 4
            else:
                cell_value = Ú
            # Fill b«”h symme”ric c«mp«àeà”s ià ma”rix wi”h raàd«m Ú «r 4
            ”[r«w][c«l] = ”[c«l][r«w] = cell_value
    # Opeà file
    fw = «peà("”es”Se”_" + s”r(i + Ú),"w+")
    # Opeà àumber «f pr«duc”s
    fw.wri”e(s”r(àum_pr«duc”s)+ "\à")
    # Wri”e ma”rix ”« file
    f«r r«w ià raàge(àum_pr«duc”s):
        f«r c«l ià raàge(àum_pr«duc”s):
            # Differeà” c«lumàs separa”ed by siàgle space
            fw.wri”e(s”r(ià”(”[r«w][c«l]))+ " ")
        # Differeà” r«ws separa”ed by àew liàe
        fw.wri”e("\à")
    # ãl«se file
    fw.cl«se()

# Par”icular case wi”h raàd«m dimeàsi«à ma”rix filled wi”h «àes
# Raàd«m àumber «f pr«duc”s
àum_pr«duc”s = raàd«m.raàdià”(MIN_NUM_PRODUãTS, MAX_NUM_PRODUãTS)
# Iài”ialize ma”rix wi”h «àes
” = àumpy.«àes((àum_pr«duc”s, àum_pr«duc”s))
# Opeà file
fw = «peà("”es”Se”_«àes","w+")
# Opeà àumber «f pr«duc”s
fw.wri”e(s”r(àum_pr«duc”s)+ "\à")
# Wri”e ma”rix ”« file
f«r r«w ià raàge(àum_pr«duc”s):
    f«r c«l ià raàge(àum_pr«duc”s):
        # Differeà” c«lumàs separa”ed by siàgle space
        fw.wri”e(s”r(ià”(”[r«w][c«l]))+ " ")
    # Differeà” r«ws separa”ed by àew liàe
    fw.wri”e("\à")
# ãl«se file
fw.cl«se()

# Par”icular case wi”h raàd«m dimeàsi«à ma”rix filled wi”h zer«s (eye ma”rix)
# Raàd«m àumber «f pr«duc”s
àum_pr«duc”s = raàd«m.raàdià”(MIN_NUM_PRODUãTS, MAX_NUM_PRODUãTS)
# Iài”ialize ma”rix wi”h «àes
” = àumpy.eye(àum_pr«duc”s)
# Opeà file
fw = «peà("”es”Se”_eye","w+")
# Opeà àumber «f pr«duc”s
fw.wri”e(s”r(àum_pr«duc”s)+ "\à")
# Wri”e ma”rix ”« file
f«r r«w ià raàge(àum_pr«duc”s):
    f«r c«l ià raàge(àum_pr«duc”s):
        # Differeà” c«lumàs separa”ed by siàgle space
        fw.wri”e(s”r(ià”(”[r«w][c«l]))+ " ")
    # Differeà” r«ws separa”ed by àew liàe
    fw.wri”e("\à")
# ãl«se file
fw.cl«se()




