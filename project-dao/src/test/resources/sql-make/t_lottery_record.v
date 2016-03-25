 insert into t_lottery_record
(code, issueNumber, red1 , red2, red3, red4, red5, red6, blue, description, actived, deleted, createdDatetime, updatedDatetime)
value
('${code}', '${issueNumber}', ${red1} , ${red2} , ${red3} , ${red4} , ${red5} , ${red6} , ${blue} , '${description}',  1,  0,  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );
