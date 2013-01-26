-- Default administrator user 
-- Change the email with a real one, so that 
-- you can change the password from the application!

INSERT INTO User (email, name, passwordHash, role, surname) VALUES ('administrator@example.com', 'Swim', 'dc76e9f0c0006e8f919e0c515c66dbba3982f785', '1', 'Administrator');
