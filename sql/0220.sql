SELECT OTHER.ename, OTHER.hiredate, MGR.ename, MGR.hiredate
	FROM employee OTHER, employee MGR
	WHERE OTHER.manager=MGR.eno
	AND OTHER.hiredate<=MGR.hiredate;

SELECT OTHER.ename, OTHER.hiredate
	FROM employee WARD, employee OTHER
	WHERE WARD.ename='WARD'
	AND WARD.hiredate<OTHER.hiredate
	order by hiredate;

SELECT ME.ename AS 이름, ME.dno AS 부서번호, OTHER.ename AS 동료
	FROM employee ME, employee OTHER
	WHERE ME.ename='SCOTT'
	AND ME.dno=OTHER.dno
	AND OTHER.ename!='SCOTT'

SELECT EMP.ename AS "Employee",
			EMP.manager AS "Emp#",
			MGR.eno AS "Manager",
			MGR.ename AS "Mgr#"
	FROM employee EMP, employee MGR
	WHERE EMP.manager=MGR.eno(+)
	ORDER BY EMP.eno DESC;

SELECT EMPLOYEES.ename AS "사원이름",
				MANAGER.ename AS "직속상관이름"
	FROM employee EMPLOYEES, employee MANAGER
	WHERE EMPLOYEES.manager = MANAGER.eno;

SELECT ename, job, dno, dname
	FROM employee NATURAL JOIN DEPARTMENT
	WHERE loc='NEW YORK'

SELECT E.ename, D.dname
	FROM employee E, department D
	WHERE E.dno=D.dno
	AND E.ename LIKE '%A%'

SELECT ename, dname, loc
	FROM employee NATURAL JOIN department
	WHERE commission>=0;

SELECT dno, job, loc
	FROM employee INEER JOIN department
	USING(dno)
	WHERE dno=10;

SELECT ename, dname, loc
	FROM employee E JOIN department D
	ON E.dno=D.dno

SELECT E.ename, E.dno, D.dname
	FROM employee E, department D
	WHERE E.dno=D.dno
	AND E.ename='SCOTT'

SELECT eno, ename, E.dno, dname FROM employee E, department D
	WHERE E.DNO=D.DNO(+)

--OUTER조인
SELECT e1.ename ||'직속 상관은'|| e2.ename
	FROM employee e1 join employee e2
	on e1.manager=e2.eno(+)

SELECT e1.ename ||'직속 상관은'|| e2.ename
	FROM employee e1 join employee e2
	on e1.manager=e2.eno

--natural 조인
SELECT eno, ename, dno, dname FROM employee E NATURAL JOIN department D

--eq 조인 
SELECT eno, ename, E.dno, dname FROM employee E, department D
	WHERE E.DNO=D.DNO