DO
$$
BEGIN

  IF NOT EXISTS (
    SELECT 1 
    FROM pg_database 
    WHERE datname = 'cmp1611_paulo_artur'
  ) THEN

    CREATE DATABASE cmp1611_paulo_artur
    WITH 
      ENCODING = 'UTF8'
      LC_COLLATE = 'en_US.UTF-8'
      LC_CTYPE = 'en_US.UTF-8'
      TEMPLATE = template0;
  END IF;
END
$$;
