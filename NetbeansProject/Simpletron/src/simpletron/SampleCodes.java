/*

--SENTINEL-CONTROLLED LOOP TO READ 10 POSITIVE NUMBERS AND DISPLAY SUM

00	+1008	//read A
01	+2008	//load A
02	+4106	//branch negative to 06
03	+3009	//add C
04	+2109	//store C
05	+4000	//branch to 00
06	+1109	//write C
07	+4300	//halt
08	+0000	//variable A
09	+0000	//Result C

--COUNTER-CONTROLLED LOOP TO READ NUMBERS AND DISPLAY AVERAGE

00	+1019	//read A-7
01	+1022	//read D-1
02	+1020	//read B-1,1,1,1,1,1,1,1,
03	+2020	//load B
04	+3021	//add C
05	+2121	//store C
06	+2023	//load E
07	+3022  	//add D
08	+2123  	//store E
09	+2019  	//load A
10	+3122  	//subtract D
11	+2119  	//store A
12	+4214  	//branch zero to 13
13	+4002	//branch to 02
14	+2021	//load C
15	+3225  	//divide E
16	+2121  	//store C
17	+1121  	//write C
18	+4300	//halt
19	+0000	//variable A
20	+0000	//Variable B
21	+0000	//Result C
22	+0000	//variable D
23	+0000	//Variable E

--COUNTER-CONTROLLED LOOP TO DETERMINE & DISPLAY LARGEST NUMBER IN A SET

00	+1015	//read A - 5
01	+1016	//read B - 1
02	+1017	//read C
03	+2017	//load C
04	+3118	//subtract D
05	+4108  	//branch negative to 08
06	+2017	//load C
07	+2118  	//store D
08	+2015  	//load A
09	+3116  	//subtract B
10	+4213  	//branch zero to 13
11	+2115  	//store A
12	+4002	//branch to 02
13	+1118  	//write C
14	+4300	//halt
15	+0000	//variable A
16	+0000	//Variable B
17	+0000	//Variable C
18	+0000	//Result D

----

00	+1010  	//read C
01	+1009  	//read A
02	+2009	//load A
03	+3010	//add C
04	+4107	//branch negative to 06
05	+2110	//store C
06	+4001	//branch to 01
07	+1110	//write C
08	+4300	//halt
09	+0000	//variable A
10	+0000	//Result C


00	+1019	//read A-7
01	+1022	//read D-1
02	+1020	//read B-1,1,1,1,1,1,1,1,
03	+2020	//load B
04	+3021	//add C
05	+2121	//store C
06	+2023	//load E
07	+3022  	//add D
08	+2123  	//store E
09	+2019  	//load A
10	+3122  	//subtract D
11	+2119  	//store A
12	+4214  	//branch zero to 13
13	+4002	//branch to 02
14	+2023	//load E
15	+3220  	//divide C
16	+2121  	//store C
17	+1121  	//write C
18	+4300	//halt
19	+0000	//variable A
20	+0000	//Variable B
21	+0000	//Result C
22	+0000	//variable D
23	+0000	//Variable E




00	+1015	//read A - 5
01	+1016	//read B - 1
02	+1017	//read C
03	+2017	//load C
04	+3118	//subtract D
05	+4108  	//branch negative to 08
06	+2017	//load C
07	+2118  	//store D
08	+2015  	//load A
09	+3116  	//subtract B
10	+4213  	//branch zero to 13
11	+2115  	//store A
12	+4002	//branch to 02
13	+1118  	//write C
14	+4300	//halt
15	+0000	//variable A
16	+0000	//Variable B
17	+0000	//Variable C
18	+0000	//Result D

*/
