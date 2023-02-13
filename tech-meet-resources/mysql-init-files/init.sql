CREATE USER IF NOT EXISTS 'todolistuser'@'%' IDENTIFIED BY 'todolistpass';
GRANT ALL PRIVILEGES ON techmeet.* TO 'todolistuser'@'%';