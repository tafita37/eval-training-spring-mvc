ALTER TABLE pack ALTER COLUMN photo_pack TYPE text USING photo_pack::text;

ALTER TABLE pack
ALTER COLUMN date_creation_pack SET DEFAULT now()::date,
ALTER COLUMN date_creation_pack SET NOT NULL;
