DO
$$
BEGIN
  -- Verifica se o banco de dados já existe
  IF NOT EXISTS (
    SELECT 1 
    FROM pg_database 
    WHERE datname = 'cmp1611_paulo_artur'
  ) THEN
    -- Se não existir, cria o banco de dados
    CREATE DATABASE cmp1611_paulo_artur
    WITH 
      ENCODING = 'UTF8'
      LC_COLLATE = 'en_US.UTF-8'
      LC_CTYPE = 'en_US.UTF-8'
      TEMPLATE = template0;
  END IF;
END
$$;
