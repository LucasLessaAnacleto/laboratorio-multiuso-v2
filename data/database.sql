create database if not exists lab_multiuso;

SET SESSION cte_max_recursion_depth = 5000;
create view agenda_mestre as
select v.*, 
	concat(date_format(v.dt,'%Y%m%d%H_'), e.id) as id,
    r.id as reserva_id,
    e.id as espaco_id
from (
	WITH RECURSIVE calendar (dt) AS (
		SELECT date_format('2025-11-30 17:00:00', '%Y-%m-%d %H:00:00')
		UNION ALL
		SELECT dt + INTERVAL 1 HOUR
		FROM calendar
	WHERE dt + INTERVAL 1 HOUR <= NOW() + INTERVAL 1 MONTH
	)
	SELECT dt, year(dt) as ano , month(dt) as mes, day(dt) as dia, dayofweek(dt) as dia_semana, hour(dt) as hora FROM calendar 
	where HOUR(dt) >= 08 and HOUR(dt) <= 22 and dayofweek(dt) <> 1
) v
cross join (select distinct id from espaco) e
left join reserva r
	on r.data_reserva = v.dt
	and r.espaco_id = e.id;