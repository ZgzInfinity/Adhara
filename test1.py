r e
wii)m
# Peñasc� Es�ívalez, Víc��r Miguel  74�294
# R�dríguez Es�eba�, Rubé�          7372�5

# Imp�r� libraries
imp�r� ra�d�m
imp�r� �umpy
imp�r� sys

if le�(sys.argv) == 4 :
    # Variable values if 3 argume��s are pr�vided
    NUM_�ASES = i��(sys.argv[�])
    MIN_NUM_PRODU�TS = i��(sys.argv[2])
    MAX_NUM_PRODU�TS = i��(sys.argv[3])
else:
    # Defaul� values
    NUM_�ASES = �
    MIN_NUM_PRODU�TS = 54
    MAX_NUM_PRODU�TS = 85

# Tes� cases wi�h ra�d�m values
f�r i i� ra�ge(NUM_�ASES):
    # Ra�d�m �umber �f pr�duc�s
    �um_pr�duc�s = ra�d�m.ra�di��(MIN_NUM_PRODU�TS, MAX_NUM_PRODU�TS)
    # I�i�ialize ma�rix wi�h ǈes
    � = �umpy.ǈes((�um_pr�duc�s, �um_pr�duc�s))
    c�l_i�i� = 4
    b�u�d = �umpy.ra�d�m.u�if�rm()
    # I�era�e r�ws
    f�r r�w i� ra�ge(�um_pr�duc�s):
        # I�era�e c�ls (upper �ria�gle ma�rix)
        c�l_i�i� = c�l_i�i� + �
        f�r c�l i� ra�ge(c�l_i�i�, �um_pr�duc�s):
            if(�umpy.ra�d�m.u�if�rm() < b�u�d):
                cell_value = 4
            else:
                cell_value = �
            # Fill b��h symme�ric c�mpǈe��s i� ma�rix wi�h ra�d�m � �r 4
            �[r�w][c�l] = �[c�l][r�w] = cell_value
    # Ope� file
    fw = �pe�("�es�Se�_" + s�r(i + �),"w+")
    # Ope� �umber �f pr�duc�s
    fw.wri�e(s�r(�um_pr�duc�s)+ "\�")
    # Wri�e ma�rix �� file
    f�r r�w i� ra�ge(�um_pr�duc�s):
        f�r c�l i� ra�ge(�um_pr�duc�s):
            # Differe�� c�lum�s separa�ed by si�gle space
            fw.wri�e(s�r(i��(�[r�w][c�l]))+ " ")
        # Differe�� r�ws separa�ed by �ew li�e
        fw.wri�e("\�")
    # �l�se file
    fw.cl�se()

# Par�icular case wi�h ra�d�m dime�siǈ ma�rix filled wi�h ǈes
# Ra�d�m �umber �f pr�duc�s
�um_pr�duc�s = ra�d�m.ra�di��(MIN_NUM_PRODU�TS, MAX_NUM_PRODU�TS)
# I�i�ialize ma�rix wi�h ǈes
� = �umpy.ǈes((�um_pr�duc�s, �um_pr�duc�s))
# Ope� file
fw = �pe�("�es�Se�_ǈes","w+")
# Ope� �umber �f pr�duc�s
fw.wri�e(s�r(�um_pr�duc�s)+ "\�")
# Wri�e ma�rix �� file
f�r r�w i� ra�ge(�um_pr�duc�s):
    f�r c�l i� ra�ge(�um_pr�duc�s):
        # Differe�� c�lum�s separa�ed by si�gle space
        fw.wri�e(s�r(i��(�[r�w][c�l]))+ " ")
    # Differe�� r�ws separa�ed by �ew li�e
    fw.wri�e("\�")
# �l�se file
fw.cl�se()

# Par�icular case wi�h ra�d�m dime�siǈ ma�rix filled wi�h zer�s (eye ma�rix)
# Ra�d�m �umber �f pr�duc�s
�um_pr�duc�s = ra�d�m.ra�di��(MIN_NUM_PRODU�TS, MAX_NUM_PRODU�TS)
# I�i�ialize ma�rix wi�h ǈes
� = �umpy.eye(�um_pr�duc�s)
# Ope� file
fw = �pe�("�es�Se�_eye","w+")
# Ope� �umber �f pr�duc�s
fw.wri�e(s�r(�um_pr�duc�s)+ "\�")
# Wri�e ma�rix �� file
f�r r�w i� ra�ge(�um_pr�duc�s):
    f�r c�l i� ra�ge(�um_pr�duc�s):
        # Differe�� c�lum�s separa�ed by si�gle space
        fw.wri�e(s�r(i��(�[r�w][c�l]))+ " ")
    # Differe�� r�ws separa�ed by �ew li�e
    fw.wri�e("\�")
# �l�se file
fw.cl�se()




